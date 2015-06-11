'use strict';

var myApp = angular
	.module('app', [
		'ngRoute',
		'ngCookies',
		'angular-growl',
		'appCtrls'
	]).config(['$routeProvider', 'growlProvider',
	function($routeProvider, growlProvider) {
		$routeProvider.
			when('/login', {
				templateUrl: 'templates/login.html'
			}).
			when('/home', {
				templateUrl: 'templates/home.html',
				controller: 'homeCtrl'
			}).
			when('/auctions', {
				templateUrl: 'templates/auctions.html',
				controller: 'auctionsCtrl'
			}).
			when('/messages', {
				templateUrl: 'templates/messages.html',
				controller: 'messagesCtrl'
			}).
			when('/newAuction', {
				templateUrl: 'templates/newAuction.html',
				controller: 'newAuctionCtrl'
			}).
			when('/newMessage', {
				templateUrl: 'templates/newMessage.html',
				controller: 'newMessageCtrl'
			}).
			otherwise({
				redirectTo: '/home'
			});
		growlProvider.onlyUniqueMessages(false);
}]);
	
myApp.factory('Page', function () {
	var title = 'Default';
	return {
		title: function () { return title; },
		setTitle: function (newTitle) { title = newTitle; }
	}
});

myApp.factory('User', function () {
	var user = 'no user';
	var isLogged = false;
	return {
		user: function () { return user; },
		setUser: function (newUser) { user = newUser; },
		isLogged: function () { return isLogged; },
		setIsLogged: function (newValue) { isLogged = newValue; },
		clear: function () { user = 'no user';isLogged = false; }
	}
});

myApp.factory('LoginService', function ($http, $cookieStore, $location, User) {
	return {
		login: function(user) {
			console.log("LoginService!!! " + user.email + " " + user.password);
			/*var storedUser = $http.get('jsons/users.json')
				.success(function (data) {
					if (user.email === data.email && user.password === data.password) {
						$cookieStore.put("userEmail", user.email);
						$cookieStore.put("userPassword", user.password);
						User.setIsLogged(true);
						User.setUser(user.email);
						$state.go('http://www.google.gr', {});
					} else {
						location.path('../welcome.html');
					}
				});*/
			/*var storedUser = $http.get('/auctions/Login')
				.success(function (data) {
					console.log("server!!! " + data.userName + " " + data.message);
				});*/
			$http.post('/auctions/Login', {name: "devora", surName: "boba"})
				.success(function (data) {
					console.log("sessionId = " + data.id);
					$cookieStore.put("sessionId", data.id);
					if ($cookieStore.get("test")) {
						console.log("undef defed!!!!");
					} else {
						console.log("undef undefed!!!!");
					}
				});
		}
	}
});

myApp.factory('SignupService', function ($http, $cookieStore, $location, User) {
	return {
		signup: function(user) {
			console.log("SignupService!!! " + user.email + " " + user.password);
			/*var storedUser = $http.get('jsons/users.json')
				.success(function (data) {
					if (user.email === data.email && user.password === data.password) {
						$cookieStore.put("userEmail", user.email);
						$cookieStore.put("userPassword", user.password);
						User.setIsLogged(true);
						User.setUser(user.email);
						$state.go('http://www.google.gr', {});
					} else {
						location.path('../welcome.html');
					}
				});*/
			$http.post('/auctions/SignUp', user)
				.success(function (data) {
					console.log("signup called succesfully!");
				})
		}
	}
});