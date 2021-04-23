(function() {
	var loginFactory = function($http) {

		var factory = {};

		factory.authentifier = function(loginBean) {
			return $http.post('rest/authentifier', loginBean);
		};

		return factory;
	};

	loginFactory.$inject = [ '$http' ];

	angular.module('banqueApp').factory('loginFactory', loginFactory);

}());