(function() {

	var CompteListeController = function($scope, $log, $location, compteFactory) {
		$log.debug("In CompteListeController");

		$scope.message = {
			error : null,
			information : null
		};

		$scope.sortBy = 'id';
		$scope.reverse = false;

		$scope.doSort = function(propName) {
			$scope.sortBy = propName;
			$scope.reverse = !$scope.reverse;
		};

		// L'id de l'utilisateur est dans la session cote serveur
		compteFactory.lister().success(function(data) {
			$scope.message.error = null;
			$log.debug('Listage des comptes effectue');
			$log.debug(data);
			$scope.comptes = data.liste;
		}).error(function(data) {
			// TODO Gerer le cas où on n'est pas connecte
			// et où il faut repartir vers la page login
			$log.error('Erreur dans CompteListeController');
			$scope.message.error = "Erreur lors de la recuperation de vos comptes ";
			if (data != null && data.errorMessage != null) {
				$scope.message.error += data.errorMessage;
			}
		});

		$scope.goMenu = function() {
			$location.url('/menu');
		};

	};

	CompteListeController.$inject = [ '$scope', '$log', '$location', 'compteFactory' ];

	angular.module('banqueApp').controller('CompteListeController', CompteListeController);
}());