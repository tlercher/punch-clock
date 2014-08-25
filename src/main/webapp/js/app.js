var app = angular.module('punchClock', ['ngRoute', 'ngQuickDate', 'punchClockControllers']);

app.config(function(ngQuickDateDefaultsProvider) {
    // Configure with icons from font-awesome
    return ngQuickDateDefaultsProvider.set({
        closeButtonHtml: "<i class='fa fa-times'></i>",
        buttonIconHtml: "<i class='fa fa-clock-o'></i>",
        nextLinkHtml: "<i class='fa fa-chevron-right'></i>",
        prevLinkHtml: "<i class='fa fa-chevron-left'></i>",
        // Take advantage of Sugar.js date parsing
        parseDateFunction: function(str) {
            d = Date.create(str);
            return d.isValid() ? d : null;
        }
    });
});


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
