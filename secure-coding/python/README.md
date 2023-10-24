# How to Install a Virtual Environment, Install Flask with Pip, and Run the Insecure Template

This code is a Python Flask application that renders a simple HTML template. To run this application, you will need to install a virtual environment, install Flask with pip, and then run the `insecure_template.py` file.

## Installing a Virtual Environment

To install a virtual environment, follow these steps:

1. Open a terminal window.
2. Install the virtualenv package by running the following command: `pip install virtualenv`.
3. Create a new virtual environment by running the following command: `virtualenv venv`.
4. Activate the virtual environment by running the following command: `source venv/bin/activate`.

## Installing Flask with Pip

Flask is a lightweight web framework that allows you to build web applications quickly and easily. To install Flask with pip, follow these steps:

1. Make sure that your virtual environment is activated.
2. Install Flask by running the following command: `pip install flask`.

## Running the Insecure Template

To run the insecure template, follow these steps:

1. Make sure that your virtual environment is activated.
2. Navigate to the directory where the `insecure_template.py` file is located.
3. Run the following command: `python insecure_template.py`.
4. Open a web browser and go to `http://localhost:5000/?name=User`. You should see a simple HTML page that says "Hello, User!".
