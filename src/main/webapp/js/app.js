var app = angular.module('punchClock', ['ngRoute', 'punchClockControllers']);

app.config(['$routeProvider', function($routeProvider) {
   $routeProvider.
       when('/index', {
           templateUrl: 'templates/list.html',
           controller: 'EntryController'
       }).
       otherwise({
           redirectTo: '/index'
       });
}]);