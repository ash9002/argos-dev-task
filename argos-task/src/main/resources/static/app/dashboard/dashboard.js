angular.module('myApp.dashboard', [
        'ui.router',
        'ngTable',
        'smart-table',
        'ui.bootstrap',
        'tableSort'
    ])

    .config(function config($stateProvider, $httpProvider) {
        $stateProvider
            .state('dashboard', {
                url: '/dashboard',
                controller: 'DashboardCtrl',
                templateUrl: 'dashboard/dashboard.html'
            })
    })

    .controller('DashboardCtrl', ['$scope', '$location', '$http','NgTableParams',
        function($scope, $location, $http, NgTableParams) {

            $http.get('http://localhost:8080/api/products')
                .success(function(data, status, headers, config) {
                    data.sort(function (a, b) {
                        return parseFloat(parseFloat(b.temperature) - (a.temperature))
                    });
                    $scope.data = data.slice(0,10);
                    console.log($scope.data)

                })
                .error(function(data, status, headers, config) {
                    // log error
                });

            $http.get('http://localhost:8080/api/recent-products')
                .success(function(data, status, headers, config) {
                    data.sort(function (a, b) {
                        return parseFloat(parseFloat(b.price) - (a.price))
                    });
                    $scope.recentData = data.slice(0,10);
                    console.log($scope.data)

                })
                .error(function(data, status, headers, config) {
                    // log error
                });
    }]);