(function() {
	var operationFactory = function($http) {

		var factory = {};

		factory.lister = function(operationBean) {
			// ---
			// IMPORTANT : En Java on ne peut pas faire de GET avec un
			// RequestBody, c'est pour cela que l'on fait du put ici
			// ---
			return $http.put('rest/operations', operationBean);
		};

		return factory;
	};

	operationFactory.$inject = [ '$http' ];

	angular.module('banqueApp').factory('operationFactory', operationFactory);

}());