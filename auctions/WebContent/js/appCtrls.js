'use strict';

var myApp = angular.module('appCtrls', [])

myApp.controller('titleCtrl', ['$scope', 'Page',
	function($scope, Page){
		$scope.Page = Page;
}]);

myApp.controller('loginCtrl', ['$scope', 'Page', 'LoginService', 'User',
	function($scope, Page, LoginService, User){
		Page.setTitle("signIn/signUp");
		$scope.User = User;
		$scope.login = function () {
			var user = {
				email: $scope.email,
				password: $scope.password
			}
			LoginService.login(user);
			$scope.email = "";
			$scope.password = "";
		}
}]);

myApp.controller('logoutCtrl', ['$scope', 'Page', 'LogoutService', 'User',
	function($scope, Page, LogoutService, User){
		$scope.User = User;
		$scope.logout = function () {
			var user = {
				
			}
			LogoutService.login(user);
		}
}]);

myApp.controller('signupCtrl', ['$scope', 'Page', 'SignupService', 'User', 'growl',
	function($scope, Page, SignupService, User, growl){
		$scope.User = User;
		$scope.signup = function () {
			var correct = true;
			var user = {
				username: $scope.username,
				firstName: $scope.first_name,
				lastName: $scope.last_name,
				email:$scope.email,
				emailConfirm:$scope.email_confirm,
				password: $scope.password,
				passwordConfirm: $scope.password_confirm,
				address: $scope.address,
				postalCode: $scope.postal_code,
				town: $scope.town,
				country: $scope.country,
				telephone: $scope.telephone,
				taxRegistrationNumber: $scope.tax_registration_number
			}
			if ($scope.email !== $scope.email_confirm) {
				growl.info("Emails are different. Please give your email again.", {title: "Form"});
				$scope.email = "";
				$scope.email_confirm = "";
				correct = false;
			}
			if ($scope.password !== $scope.password_confirm) {
				growl.info("Passwords are different. Please give your password again.", {title: "Form"});
				$scope.password = "";
				$scope.email_password = "";
				correct = false;
			}
			if (correct){
				SignupService.signup(user);
				$scope.username = "";
				$scope.first_name = "";
				$scope.last_name = "";
				$scope.email = "";
				$scope.email_confirm = "";
				$scope.password = "";
				$scope.password_confirm = "";
				$scope.address = "";
				$scope.postal_code = "";
				$scope.town = "";
				$scope.country = "";
				$scope.telephone = "";
				$scope.tax_registration_number = "";
			}
		}
}]);

myApp.controller('userCtrl', ['$rootScope', '$scope', '$http', 'User',
	function($rootScope, $scope, $http, User){
		$scope.User = User;
		$http.get("/auctions/rest/User/get")
			.success(function (response) {
				console.log(response);
				if (response.status == "nok") {
					console.log("not a user.");
				} else {
					$scope.user = response;
				}
			});
		$scope.update = function (user) {
			console.log("update worx.");	
		}
}]);

myApp.controller('homeCtrl', ['$scope', 'Page', 'User',
	function($scope, Page, User){
		Page.setTitle("home");
		User.setUser("maenolis");
		User.setIsLogged(true);
		$scope.User = User;
}]);

myApp.controller('auctionsCtrl', ['$rootScope', '$scope', '$http', '$location', 'Page', 'User',
	function ($rootScope, $scope, $http, $location, Page, User) {
		$scope.User = User;
		Page.setTitle("auctions");
		
		if ($rootScope.search && $rootScope.searched && $rootScope.searched == true) {
			$http.post('/auctions/rest/Auction/search', $rootScope.search).success(function (response) {
				console.log(response);
				$scope.auctions = response.auctionRetObject;
				dateCorrection($scope.auctions, "auction");
			});
			$rootScope.search = {};
			$rootScope.searched = false;
		} else {
			$http.get('/auctions/rest/Auction/all')
			.then(function(response) {
				console.log(response);
				$scope.auctions = response.data.auctionRetObject;
				dateCorrection($scope.auctions, "auction");
			}, function(response){
				console.log(response);
			});
		}
		
		
			$scope.openAuction = function (auction) {
				console.log("openAuction worx");
				console.log(auction);
				$rootScope.auction = auction;
				$location.path("/auction");
			}
		
	}
]);

myApp.controller('messagesCtrl', ['$rootScope', '$scope', '$http', 'Page', 'User', '$location',
	function ($rootScope, $scope, $http, Page, User, $location) {
		$scope.User = User;
		Page.setTitle("messages");
		
		$http.get('/auctions/rest/Message/all')
		.then(function(response) {
				console.log("RESPONSE");
				console.log(response);
				$scope.messages = response.data.messageRetObject;
				dateCorrection($scope.messages, "message");
				console.log($scope.messages);
			}, function(response){
				console.log(response);
			});
		
		$scope.reply = function (message) {
			$rootScope.message = message;
			$location.path("/message");
		}
	}
]);

myApp.controller('newMessageCtrl', ['$scope', '$http', 'Page', 'NewMessageService',
	function ($scope, $http, Page, NewMessageService) {
		Page.setTitle("New Message");
		$scope.newMessage = function () {
			var message = {
				messageText: $scope.messageText
			}
			NewMessageService.newMessage(message);
			$scope.messageText = "";
		}
	}
]);

myApp.controller('newAuctionCtrl', ['$scope', '$http', 'Page', 'NewAuctionService',
	function ($scope, $http, Page, NewAuctionService) {
		Page.setTitle("newAuction");
		$scope.newAuction = function () {
			var auction = {
				productName: $scope.productName,
				categories: $scope.categories,
				buyPrice: $scope.buyPrice,
				firstBid:$scope.firstBid,
				startTime:$scope.startTime,
				endTime: $scope.endTime,
				description: $scope.description
			}
			NewAuctionService.newAuction(auction);
			$scope.productName = "";
			$scope.categories = "";
			$scope.buyPrice = "";
			$scope.firstBid = "";
			$scope.startTime = "";
			$scope.endTime = "";
			$scope.description = "";
		}
	}
]);

myApp.controller('newBidCtrl', ['$scope', '$http', 'Page', 'NewBidService',
	function ($scope, $http, Page, NewBidService) {
		Page.setTitle("newBid");
		$scope.newBid = function () {
			var bid = {
				ammount: $scope.ammount
			}
			NewBidService.newBid(bid);
			$scope.bid = "";
		}
	}
]);

myApp.controller('getClassCtrl', ['$scope', '$location', 'Page', function($scope, $location){
	$scope.isActive = function (viewLocation) {
		return viewLocation == $location.path();
	}
}]);

myApp.controller('auctionCtrl', ['$rootScope', '$scope', '$location', '$http', 'Page', 'User', 'growl',
		function($rootScope, $scope, $location, $http, Page, User, growl){
			console.log("Auction ctrl!");
			Page.setTitle("Auction");
			
			$scope.auction = $rootScope.auction;
			$scope.map = { center: { latitude: $scope.auction.lat, longitude: $scope.auction.lon }, zoom: 14 };
			$scope.marker = {
				id: 0,
				coords: {
					latitude: $scope.auction.lat,
					longitude: $scope.auction.lon
				},
				options: { draggable: true },
				events: {
					dragend: function (marker, eventName, args) {
						$log.log('marker dragend');
						var lat = marker.getPosition().lat();
						var lon = marker.getPosition().lng();
						$log.log(lat);
						$log.log(lon);

						$scope.marker.options = {
							draggable: true,
							labelContent: "lat: " + $scope.marker.coords.latitude + ' ' + 'lon: ' + $scope.marker.coords.longitude,
							labelAnchor: "100 0",
							labelClass: "marker-labels"
						};
					}
				}
			};
			
			$scope.newBid = function (auction) {
				console.log("bid worx.");
				console.log(auction);
			}
			$http.post('/auctions/rest/Bid/bids', $scope.auction).success(function (response) {
				console.log(response);
				$scope.bids = response.bidRetObject;
				dateCorrection($scope.bids, "bid");
			});
			
			$scope.searchAuction = function() {
				var search = {};
				search.productName = $scope.searchProductName;
				search.categories = $scope.searchCategories;
				search.buyPriceLow = $scope.searchBuyPriceLow;
				search.buyPriceHigh = $scope.searchBuyPriceHigh;
				search.firstBidLow = $scope.searchFirstBidLow;
				search.firstBidHigh = $scope.searchFirstBidHigh;
				search.description = $scope.searchDescription;
				search.town = $scope.searchTown;
				search.country = $scope.searchCountry;
				
				var correct = true;
				if (search.buyPriceLow < 0) {
					$scope.searchBuyPriceLow = "";
					growl.info("searchBuyPriceLow must be positive.", {title: "form check!"});
				}
				if (search.buyPriceHigh < 0) {
					$scope.searchBuyPriceHigh = "";
					growl.info("searchBuyPriceHigh must be positive.", {title: "form check!"});
				}
				if (search.buyPriceHigh < search.buyPriceLow) {
					$scope.searchBuyPriceHigh = "";
					$scope.searchBuyPriceLow = "";
					growl.info("searchBuyPriceHigh must be greater than searchBuyPriceLow.", {title: "form check!"});
				}
				
				if (search.firstBidLow < 0) {
					$scope.searchFirstBidLow = "";
					growl.info("searchFirstBidLow must be positive.", {title: "form check!"});
				}
				if (search.firstBidHigh < 0) {
					$scope.searchFirstBidHigh = "";
					growl.info("searchFirstBidHigh must be positive.", {title: "form check!"});
				}
				if (search.firstBidHigh < search.firstBidLow) {
					$scope.searchFirstBidHigh = "";
					$scope.searchFirstBidLow = "";
					growl.info("searchFirstBidHigh must be greater than searchFirstBidLow.", {title: "form check!"});
				}
				
				if (!search.buyPriceLow) {
					search.buyPriceLow = -1.0;
				}
				if (!search.buyPriceHigh) {
					search.buyPriceHigh = -1.0;
				}
				if (!search.firstBidLow) {
					search.firstBidLow = -1.0;
				}
				if (!search.firstBidHigh) {
					search.firstBidHigh = -1.0;
				}
				
				if (correct) {
					$rootScope.search = search;
					$rootScope.searched = true;
					$location.path('/auctions');
					
					$scope.searchProductName = "";
					$scope.searchCategories = "";
					$scope.searchBuyPriceLow = "";
					$scope.searchBuyPriceHigh = "";
					$scope.searchFirstBidLow = "";
					$scope.searchFirstBidHigh = "";
					$scope.searchDescription = "";
					$scope.searchTown = "";
					$scope.searchCountry = "";
				}
			}
}]);

myApp.controller('messageCtrl', ['$rootScope', '$scope', '$location', 'Page', 'User',
		function($rootScope, $scope, $location, Page, User){
	console.log("Message ctrl!");
	Page.setTitle("Message");
	$scope.User = User;
	$scope.User.setUser("Manolis message");
	$scope.message = $rootScope.message;
	$scope.send = function (message) {
		console.log("send");
		console.log(message);
	}
}]);

myApp.controller('userPageCtrl', ['$scope', '$location', 'Page', 'User', function($scope, $location, Page, User){
	console.log("UserPage ctrl!");
	Page.setTitle("User");
	$scope.User = User;
}]);

myApp.controller('notifyCtrl', ['$scope', 'growl',
	function($scope, growl){
		$scope.showInfo = function (){
			growl.info("You 've got a new message.", {title: "Message!"});
		}
}]);

function toArray(jsonObj) {
	var array = []
	for (var i in jsonObj) {
		array.push(jsonObj[i]);
	}
	return array;
}

function dateCorrection(array, type) {
	
	var myDateArray;
	
	if (type == "auction") {
		for (var i in array) {
			myDateArray = array[i].startTime.split(/[-: ]/);
			array[i].startTime = new Date(myDateArray[2], myDateArray[1] - 1, myDateArray[0], myDateArray[3], myDateArray[4], myDateArray[5], 0);
			myDateArray = array[i].endTime.split(/[-: ]/);
			array[i].endTime = new Date(myDateArray[2], myDateArray[1] - 1, myDateArray[0], myDateArray[3], myDateArray[4], myDateArray[5], 0);
		}
	} else if (type == "message" || type == "bid") {
		for (var i in array) {
			myDateArray = array[i].time.split(/[-: ]/);
			array[i].time = new Date(myDateArray[2], myDateArray[1] - 1, myDateArray[0], myDateArray[3], myDateArray[4], myDateArray[5], 0);
		}
	}
	
}