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
				controller: 'userCtrl'
			}).
			when('/adminPanel', {
				templateUrl: 'templates/adminPanel.html',
				controller: 'adminPanelCtrl'
			}).
			when('/manageAuction', {
				templateUrl: 'templates/manageAuction.html',
				controller: 'manageAuctionCtrl'
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

myApp.factory('User', function ($http, $location, growl) {
	return {
		isLogged: function () {
			$http.get('/auctions/rest/User/isLogged').success(function (response) {
				console.log(response);
				if (response.flag == true) {
					console.log("true " + response);
				} else {
					console.log("false " + response);
					growl.info("Not logged in!", {title: "Access"});
					$location.path('/auctions');
				}
			});
		},
		isAdmin: function () {
			$http.get('/auctions/rest/Admin/isAdmin').success(function (response) {
				console.log(response);
				if (response.flag == true) {
					console.log("true " + response);
				} else {
					console.log("false " + response);
					growl.info("You are not admin!", {title: "Access"});
					$location.path('/auctions');
				}
			});
		},
		update: function (user) {
			$http.post("/auctions/rest/User/update", user).success(function(response) {
				console.log("update returned");
			});
		},
		ownsAuction: function (auction) {
			$http.post("/auctions/rest/User/owsAuction", auction).success(function(response) {
				console.log("owsAuction returned");
				if (response.flag == true) {
					console.log("true " + response);
				} else {
					console.log("false " + response);
					growl.info("This auction is not yours!", {title: "Access"});
					$location.path('/auctions');
				}
			});
		}
	}
});

myApp.factory('LoginService', function ($http, $location) {
	return {
		login: function(user) {
			console.log("LoginService!!! " + user.email + " " + user.password);
			$http.post('/auctions/rest/Login', user)
				.success(function (response) {
					if (response.status == "ok") {
						console.log("success");
						console.log(response);
						$location.path('/auctions');
					} else {
						console.log("login failure!");
					}
				});
		}
	}
});

myApp.factory('LogoutService', function ($http, $cookies, $location, User) {
	return {
		logout: function() {
			$http.get('/auctions/rest/Logout')
				.success(function (data) {
					if (data) {
						console.log(data);
					} else {
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
			$http.get("https://maps.googleapis.com/maps/api/geocode/json?address=" +
				user.address + ", " + user.town + " " + user.postalCode + ", " + user.country)
				.success(function (data) {
					user.longtitude = data["results"][0]["geometry"]["location"]["lng"];
					user.latitude = data["results"][0]["geometry"]["location"]["lat"];
					$http.post('/auctions/rest/SignUp', user)
						.success(function (data2) {
							$location.path('/home');
						});
				});
		}
	}
});

myApp.factory('AuctionService', function ($http, $cookieStore, $location, growl) {
	return {
		updateAuction: function(auction) {
			$http.post('/auctions/rest/Auction/update', auction)
					.success(function (response) {
						console.log("update returned");
						$location.path('/auctions');
					});
		},
		deleteAuction: function(auction) {
			$http.post('/auctions/rest/Auction/delete', auction)
					.success(function (response) {
						console.log("delete returned");
						$location.path('/auctions');
					});
		},
		newAuction: function(auction) {
			$http.post('/auctions/rest/Auction/new', auction)
				.success(function (data2) {
					console.log(data2);
					if (data2 === "ok") {
						$location.path('/auctions');
					} else {
						growl.info("Something went wrong!", {title: "Form"});
					}
				});
		}
	}
});

myApp.factory('NewMessageService', function ($http, $cookieStore, $location) {
	return {
		newMessage: function(message) {
				$http.post('/auctions/rest/Message/new', message)
					.success(function (data) {
						$location.path('/auctions');
					});
		}
	}
});

angular.module('app').directive('ngReallyClick', [function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            element.bind('click', function() {
                var message = attrs.ngReallyMessage;
                if (message && confirm(message)) {
                    scope.$apply(attrs.ngReallyClick);
                }
            });
        }
    }
}]);