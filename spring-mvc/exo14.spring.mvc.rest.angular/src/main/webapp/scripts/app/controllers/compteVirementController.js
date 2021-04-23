(function() {

	var CompteVirementController = function($scope, $log, $location, compteFactory) {
		$log.debug("In CompteVirementController");

		$scope.message = {
			error : null,
			information : null
		};

		// L'id de l'utilisateur est dans la session cote serveur
		// On utilise la meme factory pour recuperer tous les comptes
		compteFactory.lister().success(function(data) {
			$scope.message.error = null;
			$log.debug('Listage des comptes effectue');
			$log.debug(data);
			$scope.comptes = data.liste;
		}).error(function(data) {
			// TODO Gerer le cas où on n'est pas connecte
			// et où il faut repartir vers la page login
			$log.error('Erreur dans CompteVirementController.lister');
			$scope.message.error = "Erreur lors de la recuperation de vos comptes ";
			if (data != null && data.errorMessage != null) {
				$scope.message.error += data.errorMessage;
			}
		});

		$scope.goMenu = function() {
			$location.url('/menu');
		};

		$scope.virer = function() {
			$log.debug($scope.virementBean);
			compteFactory.faireVirement($scope.virementBean).success(function() {
				$scope.message.error = null;
				$scope.message.information = "Votre virement a ete effectue";
			}).error(function(data) {
				$log.error('Erreur dans CompteVirementController.virer');
				$scope.message.error = "Erreur lors du virement ";
				$scope.message.information = null;
				if (data != null && data.errorMessage != null) {
					$scope.message.error += data.errorMessage;
				}
			});
		};
	};

	CompteVirementController.$inject = [ '$scope', '$log', '$location', 'compteFactory' ];

	angular.module('banqueApp').controller('CompteVirementController', CompteVirementController);
}());