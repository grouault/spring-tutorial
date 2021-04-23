(function() {
	var compteFactory = function($http) {

		var factory = {};

		factory.lister = function() {
			return $http.get('rest/comptes/');
		};

		factory.faireVirement = function(virementBean) {
			return $http.put('rest/virer/', virementBean);
		};

		return factory;
	};

	compteFactory.$inject = [ '$http' ];

	angular.module('banqueApp').factory('compteFactory', compteFactory);

}());