'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ui.router',
  'ngResource',
  'ngTable',
  'smart-table',
  'tableSort',
  'ui.bootstrap',
  'myApp.dashboard'
  //defining the required modules for this application
]).

config(['$stateProvider','$urlRouterProvider', '$httpProvider', function($stateProvider, $urlRouterProvider, $httpProvider) {
  // For any unmatched url, redirect to /login
  $urlRouterProvider.otherwise("/dashboard");
}]);