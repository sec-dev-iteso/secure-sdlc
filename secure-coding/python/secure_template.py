from flask import Flask, request, render_template_string

app = Flask(__name__)

# Handle the template in a secure way to avoid XSS and RCE 
@app.route('/')
def index():
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
