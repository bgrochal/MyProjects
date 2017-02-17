/**
 * Setting up application modules.
 * Application created with Express. Uses instance of MongoDB accessed by mongoose.
 */
var express = require('express');
var app = express();
var mongoose = require('mongoose');
var morgan = require('morgan');
var bodyParser = require('body-parser');
var methodOverride = require('method-override');

/**
 * Configure MongoDB connection.
 */
mongoose.connect("mongodb://localhost/todoDB");

/**
 * Configure application.
 */
app.use(express.static(__dirname + "/public"));
app.use(morgan('dev'));
app.use(bodyParser.urlencoded({"extended": "true"}));
app.use(bodyParser.json());
app.use(bodyParser.json({type: "application/vnd.api+json"}));
app.use(methodOverride());

/**
 * Define object model.
 */
var todoModel = mongoose.model("todoModel", {
    text : String
});

/**
 * RESTful API routing.
 */
app.get("/todos", function(request, response) {
    todoModel.find(function(error, result) {
        if (error) {
            response.send(error);
        }

        response.json(result)
    });
});

app.post("/todos", function(request, response) {
    todoModel.create(
        {
            text : request.body.text,
            done : false
        },
        function(error, result) {
            if (error) {
                response.send(error);
            }

            todoModel.find(function(error, result) {
                if (error) {
                    response.send(error);
                }

                response.json(result);
            });
        }
    );
});

app.delete("/todos/:todo_id/", function(request, response) {
    todoModel.remove(
        {
            _id : request.params.todo_id
        },
        function(error, result) {
            if(error) {
                response.send(error);
            }

            todoModel.find(function(error, result) {
                if(error) {
                    response.send(error);
                }

                response.json(result);
            });
        }
    );
});

app.get("*", function(request, response) {
    response.sendfile("./public/index.html");
});

/**
 * Listen on port 8080.
 */
app.listen(8080);
console.log("Application is listening on 8080 port.");
