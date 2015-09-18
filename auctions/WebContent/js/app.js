'use strict';

var myApp = angular
	.module('app', [
		'ngRoute',
		'ngCookies',
		'angular-growl',
		'appCtrls',
		'uiGmapgoogle-maps'
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
			when('/newBid', {
				templateUrl: 'templates/newBid.html',
				controller: 'newBidCtrl'
			}).
			when('/message', {
				templateUrl: 'templates/message.html',
				controller: 'messageCtrl'
			}).
			when('/auction', {
				templateUrl: 'templates/auction.html',
				controller: 'auctionCtrl'
			}).
			when('/searchAuction', {
				templateUrl: 'templates/searchAuction.html',
				controller: 'auctionCtrl'
			}).
			when('/user', {
				templateUrl: 'templates/user.html',
				controller: 'userPageCtrl'
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

myApp.factory('User', function ($http) {
	var user = 'no user';
	var isLogged = false;
	return {
		user: function () { /*console.log("user " + user);*/ return user; },
		setUser: function (newUser) { /*console.log("setUser " + newUser);*/ user = newUser; },
		isLogged: function () { /*console.log("isLogged " + isLogged);*/ return isLogged; },
		setIsLogged: function (newValue) { /*console.log("setIsLogged " + newValue);*/ isLogged = newValue; },
		clear: function () { /*console.log("clear ");*/ user = 'no user';isLogged = false; },
		name: function () { /*console.log("name " + user);*/ return user; },
		userId: function () {
			$http.get('/auctions/rest/User/get').success(function (response) {
				console.log(response);
				if (response.status == "ok") {
					return response.id;
				} else {
					return -1;
				}
			});
		}
	}
});

myApp.factory('LoginService', function ($http, $cookieStore, $location, User) {
	return {
		login: function(user) {
			console.log("LoginService!!! " + user.email + " " + user.password);
			$http.post('/auctions/rest/Login', user)
				.success(function (data) {
					
					if (data) {
						console.log("success");
						console.log(data);
					} else {
						console.log("login failure!");
					}
				});
		}
	}
});

myApp.factory('LogoutService', function ($http, $cookies, $location, User) {
	return {
		login: function() {
			console.log("LogoutService!!!");
			User.clear();
			$cookies.skata = "aposkata";
			$http.post('/auctions/rest/Logout')
				.success(function (data) {
					
					if (data) {
						console.log("logout not null!!");
						console.log("logout success 1!");
						console.log(data);
					} else {
						console.log("logout success 2!");
					}
					var path = $location.path();
					console.log(path);
					$location.path('/auctions');
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
			$http.get("https://maps.googleapis.com/maps/api/geocode/json?address=" +
				user.address + ", " + user.town + " " + user.postalCode + ", " + user.country)
				.success(function (data) {
					user.longtitude = data["results"][0]["geometry"]["location"]["lng"];
					user.latitude = data["results"][0]["geometry"]["location"]["lat"];
					$http.post('/auctions/rest/SignUp', user)
						.success(function (data2) {
							console.log("signup called succesfully!");
							console.log(user);
							console.log(data2);
							User.setUser(data2.username);
							User.setIsLogged(true);
						});
				});
		}
	}
});

myApp.factory('NewAuctionService', function ($http, $cookieStore, $location) {
	return {
		newAuction: function(auction) {
			console.log("NewAuctionService!!! " + auction.productName);
				$http.post('/auctions/rest/NewAuction', auction)
					.success(function (data2) {
						console.log("newAuction called succesfully!");
						console.log(data2);
					});
		}
	}
});

myApp.factory('NewMessageService', function ($http, $cookieStore, $location) {
	return {
		newMessage: function(message) {
			console.log("NewMessageService!!! " + message.messageText);
				$http.post('/auctions/rest/Message/new', message)
					.success(function (data) {
						console.log("newMessage called succesfully!");
						console.log(data);
					});
		}
	}
});

myApp.factory('NewBidService', function ($http, $cookieStore, $location) {
	return {
		newBid: function(bid) {
			console.log("NewBidService!!! " + bid.ammount);
				$http.post('/auctions/rest/NewBid', bid)
					.success(function (data) {
						console.log("newBid called succesfully!");
						console.log(data);
					});
		}
	}
});