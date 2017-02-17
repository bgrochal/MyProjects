/**
 * Create module.
 */
var todoModule = angular.module('todoModule', []);

/**
 * Create controller.
 */
function todoModuleController($scope, $http) {
    $scope.formData = {};

    $http.get("/todos")
        .success(function (data) {
            $scope.todos = data;
            console.log(data)
        })
        .error(function (data) {
            console.log("An error occurred: " + data);
        });

    $scope.createTodo = function() {
        $http.post("/todos", $scope.formData)
            .success(function(data) {
                $scope.formData = {};
                $scope.todos = data;
                console.log(data);
            })
            .error(function(data) {
                console.log("An error occurred: " + data);
            });
    };

    $scope.deleteTodo = function(id) {
        $http.delete("/todos/" + id)
            .success(function(data) {
                $scope.todos = data;
                console.log(data);
            })
            .error(function(data) {
                console.log("An error occurred: " + data);
            });
    };
}
