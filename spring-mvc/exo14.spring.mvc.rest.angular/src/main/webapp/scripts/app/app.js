(function() {

	var app = angular.module('banqueApp', [ 'ngRoute' ]);

	app.config(function($routeProvider) {
		$routeProvider.when('/', {
			controller : 'LoginController',
			templateUrl : 'scripts/app/views/login.html'
		}).when('/menu', {
			templateUrl : 'scripts/app/views/menu.html'
		}).when('/compteliste', {
			controller : 'CompteListeController',
			templateUrl : 'scripts/app/views/comptes/liste.html'
		}).when('/comptevirement', {
			controller : 'CompteVirementController',
			templateUrl : 'scripts/app/views/comptes/virement.html'
		}).when('/comptehistorique/:cptId', {
			controller : 'OperationController',
			templateUrl : 'scripts/app/views/comptes/historique.html'
		});
		// .otherwise( { redirectTo: '/' } );
	});

}());