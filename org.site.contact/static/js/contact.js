var contactApp = angular.module('contactApp', [ 'mainApp', 'ui.router' ]);

angular.module('mainApp').config(function($urlRouterProvider, $stateProvider) {
	
	$urlRouterProvider.otherwise('/home');

	$stateProvider

	.state('contact', {
		url : '/contact',
		templateUrl : 'contact/partials/contact.html'
	})

});

console.log('contact app initialized');