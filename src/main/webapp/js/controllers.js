var controllers = angular.module('punchClockControllers', []);


controllers.controller('EntryController', ['$scope', '$route', '$http', function($scope, $route, $http) {
    $scope.entries = [];

    $http.get('/entrys/?sort=entryDate,DESC').success(function(data, status, headers, config) {
        if(data._embedded) {
            $scope.entries =  data._embedded.entrys;

            var type = $scope.entries[0].type;

            $("#quick_check-in").prop('disabled', (type === "Come" || type === "Marker"));
            $("#quick_check-out").prop('disabled', (type === "Leave"));
            $("#quick_add-step").prop('disabled', (type === "Leave"));
        }
    });

    $scope.deleteEntry = function(url) {
        $http.delete(url).success(function(data, status, headers, config) {
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
}]);