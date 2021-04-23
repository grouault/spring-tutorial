(function() {

	var OperationController = function($scope, $log, $location, $routeParams, operationFactory) {
		$log.debug("In OperationController");

		$scope.message = {
			error : null,
			information : null
		};

		$scope.sortBy = 'date';
		$scope.reverse = false;

		$scope.doSort = function(propName) {
			$scope.sortBy = propName;
			$scope.reverse = !$scope.reverse;
		};

		// Le cptId vient de la table de routage
		$scope.operationBean = {
			cptId : $routeParams.cptId,
			dateDebut : null,
			dateFin : null,
			credit : true,
			debit : true
		}

		// L'id de l'utilisateur est dans la session cote serveur
		$scope.rechercher = function() {
			$log.debug($scope.operationBean);
			operationFactory.lister($scope.operationBean).success(function(data) {
				$scope.message.error = null;
				$log.debug('Listage des operations effectue');
				$log.debug(data);
				$scope.operations = data.liste;
			}).error(function(data) {
				// TODO Gerer le cas où on n'est pas connecte
				// et où il faut repartir vers la page login
				$log.error('Erreur dans OperationController.lister');
				$scope.message.error = "Erreur lors de la recuperation de vos comptes ";
				if (data != null && data.errorMessage != null) {
					$scope.message.error += data.errorMessage;
				}
			});
		};

		if ($scope.operations == null) {
			$log.debug('Pas d operation dans le scope, on lance l appel par defaut');
			$scope.rechercher();
		}

		$scope.goMenu = function() {
			$location.url('/menu');
		};

		$scope.goListe = function() {
			$location.url('/compteliste');
		};
	};

	OperationController.$inject = [ '$scope', '$log', '$location', '$routeParams', 'operationFactory' ];

	angular.module('banqueApp').controller('OperationController', OperationController);
}());