app.run(function(mainMenu) {
	mainMenu.add(4, 'Sign In', 'login');
	mainMenu.add(5, 'Join', 'join');
});

app.controller('LoginCtrl', [ '$scope', function($scope) {
	console.log('In LoginController');
	$scope.message = 'hiush';
	$scope.googleLogin = function() {
		gapi.client.load('plus', 'v1', function() {
			var request = gapi.client.plus.people.get({
				'userId' : 'me'
			});
			request.execute(function(resp) {
				console.log('response is ' + JSON.stringify(resp));
				// resp.access_token = authResult['access_token'];
				// jQuery.ajax({
				// url : '/rest/googlelogin',
				// type : "POST",
				// data : JSON.stringify(resp),
				// contentType : "application/json",
				// success : function(response) {
				// window.location.href = '/index.html';
				// }
				// });
			});
		});
	};
} ]);

app.config(function($stateProvider, $urlRouterProvider) {

	$stateProvider

	.state('login', {
		url : '/login',
		templateUrl : 'login/partials/login.html',
		controller : 'LoginCtrl'
	})

	.state('join', {
		url : '/join',
		templateUrl : 'login/partials/join.html',
		controller : 'LoginCtrl'
	})

});

(function() {
	var po = document.createElement('script');
	po.type = 'text/javascript';
	po.async = true;
	po.src = 'https://apis.google.com/js/client:plusone.js';
	var s = document.getElementsByTagName('script')[0];
	s.parentNode.insertBefore(po, s);

})();

function signinCallback(authResult) {
	console.log('in signin callback with authResult: '
			+ JSON.stringify(authResult));
	gapi.client.load('plus', 'v1', function() {
		var request = gapi.client.plus.people.get({
			'userId' : 'me'
		});

		request.execute(function(resp) {
			resp.access_token = authResult['access_token'];
			console.log('response is ' + JSON.stringify(resp));
			// jQuery.ajax({
			// url : '/rest/googlelogin',
			// type : "POST",
			// data : JSON.stringify(resp),
			// contentType : "application/json",
			// success : function(response) {
			// window.location.href = '/index.html';
			// }
			// });
		});
	});
}