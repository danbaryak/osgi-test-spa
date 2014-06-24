app.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/home');
    
    $stateProvider
        
        .state('home', {
            url: '/home',
            templateUrl: 'about/partials/home.html'
        })
        
        .state('about', {
        	url: '/about',
        	templateUrl: 'about/partials/about.html'
        })
        
        .state('another', {
        	url: '/another',
        	templateUrl: 'about/partials/another.html'
        });
        
});

app.run(function(mainMenu) {
	mainMenu.appName = 'My App';
	mainMenu.appNameRef = 'home';
	
	mainMenu.add(1, 'About', 'about');
	mainMenu.add(3, 'Why', 'another');
});

app.controller('NavCtrl', ['$scope', 'mainMenu', function($scope, mainMenu) {
	$scope.menu = mainMenu;
	
}]);