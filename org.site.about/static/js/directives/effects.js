app.directive('fadeInUp', function() {
	return {
		restrict: 'A',
		replace: false,
		link: function(scope, element, attrs) {
			element.velocity({opacity: 0, translateY: 20}, 0);
			
			element.velocity({opacity: 1, translateY: 0}, {
				easing: 'easeInSine',
				duration: 500,
				delay: attrs.delay
			});
			
		}
	}
})