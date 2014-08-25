var controllers = angular.module('punchClockControllers', []);


controllers.controller('EntryController', ['$scope', '$route', '$http', function($scope, $route, $http) {
    $scope.entries = [];
    $scope.types = [];

    $http.get('/entrys/').success(function(data, status, headers, config) {
        if(data.length > 0) {
            $scope.entries =  data;

            var type = $scope.entries[0].type;

            $("#quick_check-in").prop('disabled', (type === "Come" || type === "Marker"));
            $("#quick_check-out").prop('disabled', (type === "Leave"));
            $("#quick_add-step").prop('disabled', (type === "Leave"));
        }
    });

    $http.get('/entrys/types/').success(function(data, status, headers, config) {
        $scope.types = data;
    });

    $scope.deleteEntry = function(entry) {
        $http.delete('/entrys/' + entry.id).success(function(data, status, headers, config) {
            $route.reload();
        });
    };

    $scope.addEntry = function(type) {
        var params = JSON.stringify({ 'type': type });
        $http.post('/entrys/', params, {
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            }
        });
        $route.reload();
    };

    $scope.editEntry = function(entry) {
        entry.editMode = true;
    }
    $scope.cancelEdit = function(entry) {
        entry.editMode = false;
        $http.get('/entrys/' + entry.id, entry, {
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            }
        }).success(function(data) {
            entry = data;
            $route.reload();
        });
    }
    $scope.saveEntry = function(entry) {
        entry.editMode = false;
        $http.post('/entrys/' + entry.id, entry, {
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            }
        });
    }
}]);