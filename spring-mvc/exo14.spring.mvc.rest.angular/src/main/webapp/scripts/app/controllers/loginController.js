(function() {

	var LoginController = function($scope, $log, $location, loginFactory) {
		$log.debug("In LoginController");

		$scope.message = {
			error : null,
			information : null
		};

		$scope.authentifier = function() {
			$log.debug($scope.loginBean);
			loginFactory.authentifier($scope.loginBean).success(function(data) {
				$scope.message.error = null;
				$log.debug('Utilisateur connecte');
				$log.debug(data);
				$scope.utilisateur = data;
				$scope.message.information = "Bonjour Mr " + data.nom;
				$location.url("/menu");
			}).error(function(data) {
				$scope.message.error = "Merci de vous reconnecter ";
				if (data != null && data.errorMessage != null) {
					$scope.message.error += data.errorMessage;
				}
			});
		};
	};

	LoginController.$inject = [ '$scope', '$log', '$location', 'loginFactory' ];

	angular.module('banqueApp').controller('LoginController', LoginController);
}());