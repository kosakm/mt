/**
 * 
 */
(function() {
    var app = angular.module('wasatchSocial', ['ngResource']);

    app.factory('instagramService', function($resource) {
    	return $resource('/instagram');
    });
    app.controller('InstagramController', ['$scope','$resource','instagramService', function($scope,$resource, instagramService){
      var main = this;
      main.instagrams = [];
      console.log("In the instagram controller");
      instagramService.get(function(response){
    	  console.log(JSON.stringify(response.data) +" query")
    	  main.instagrams = response.data;
      });
    }]);
  })();