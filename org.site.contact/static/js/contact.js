
app.run(function(mainMenu) {
	mainMenu.add(2, 'Contact', 'contact');
})

app.controller('ContactMessageCtrl', ['$scope', '$http', function($scope, $http) {
	$scope.message = 'hello there';
}]);

app.config(function($urlRouterProvider, $stateProvider, $injector) {

	$stateProvider
	.state('contact', {
		url : '/contact',
		templateUrl: 'contact/partials/contact.html'
	})
	
	.state('contact.message', {
		url: '/message',
		templateUrl: 'contact/partials/message.html',
		controller: 'ContactMessageCtrl'
	});
	
	
});
	