'use strict';

var myApp = angular.module('appCtrls', []);

myApp.controller('titleCtrl', ['$scope', 'Page',
	function($scope, Page){
		$scope.Page = Page;
}]);

myApp.controller('loginCtrl', ['$scope', 'Page', 'LoginService',
	function($scope, Page, LoginService){
		Page.setTitle("signIn/signUp");
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

myApp.controller('logoutCtrl', ['$scope', 'Page', 'LogoutService',
	function($scope, Page, LogoutService){
		$scope.logout = function () {
			
			LogoutService.logout();
		}
}]);

myApp.controller('signupCtrl', ['$scope', 'Page', 'SignupService', 'growl',
	function($scope, Page, SignupService, growl){
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

myApp.controller('userCtrl', ['$rootScope', '$scope', '$http', 'User', 'Page', '$location',
	function($rootScope, $scope, $http, User, Page, $location){
		Page.setTitle("User");
		User.isLogged();
		
		$http.get('/auctions/rest/User/getUser').success(function (response){
			if (response.status == "ok") {
				$scope.user = response;
			}
		});
		
		$scope.update = function () {
			if ($scope.newFirstName) {
				$scope.user.firstName = $scope.newFirstName;
				$scope.newFirstName = null;
			}
			if ($scope.newLastName) {
				$scope.user.lastName = $scope.newLastName;
				$scope.newLastName = null;
			}
			if ($scope.newCountry) {
				$scope.user.country = $scope.newCountry;
				$scope.newCountry = null;
			}
			if ($scope.newTown) {
				$scope.user.town = $scope.newTown;
				$scope.newTown = null;
			}
			if ($scope.newAddress) {
				$scope.user.address = $scope.newAddress;
				$scope.newAddress = null;
			}
			if ($scope.newTelephone) {
				$scope.user.telephone = $scope.newTelephone;
				$scope.newTelephone = null;
			}
			if ($scope.newPostalCode) {
				$scope.user.postalCode = $scope.newPostalCode;
				$scope.newPostalCode = null;
			}
			User.update($scope.user);
		}
}]);

myApp.controller('homeCtrl', ['$rootScope', '$scope', '$http', 'Page', 'User', '$location' , 'growl',
	function($rootScope, $scope, $http, Page, User, $location, growl){
		Page.setTitle("home");
		User.isLogged();
		
		$http.get('/auctions/rest/Auction/myAuctions')
			.then(function(response) {
				$scope.auctions = response.data.auctionRetObject;
				dateCorrection($scope.auctions, "auction");
			}, function(response){
				console.log(response);
		});
			
		$scope.manage = function (auction) {
			$http.post('/auctions/rest/Auction/getAuction', auction).success(function (response){
				if (response.status == "ok") {
					$rootScope.auction = response;
					$location.path('manageAuction');
				}
				else {
					growl.info("Something went wrong!.", {title: "auction management!"});
				}
			});
		}
		
		$scope.updateUser = function () {
			$location.path('/user');
		}
		
		$scope.createAuction = function () {
			$location.path('/newAuction');
		}
}]);

myApp.controller('auctionsCtrl', ['$rootScope', '$scope', '$http', '$location', 'Page', 'User',
	function ($rootScope, $scope, $http, $location, Page, User) {
		
		Page.setTitle("auctions");
		
		if ($rootScope.search && $rootScope.searched && $rootScope.searched == true) {
			$http.post('/auctions/rest/Auction/search', $rootScope.search).success(function (response) {
				$scope.auctions = response.auctionRetObject;
				dateCorrection($scope.auctions, "auction");
			});
			$rootScope.search = {};
			$rootScope.searched = false;
		} else {
			$http.get('/auctions/rest/Auction/all')
			.then(function(response) {
				$scope.auctions = response.data.auctionRetObject;
				dateCorrection($scope.auctions, "auction");
			}, function(response){
				console.log(response);
			});
		}
		
		$scope.openAuction = function (auction) {
			$rootScope.auction = auction;
			$location.path('/auction');
			/*for (var i = 0; i < $scope.auctions.length; i++) {
				if (auction.id === $scope.auctions[i].id) {
					$rootScope.auction = $scope.auctions[i];
					console.log("auction found!! " + i);
					
				}
			}*/
			
		}
		
	}
]);

myApp.controller('messagesCtrl', ['$rootScope', '$scope', '$http', 'Page', 'User', '$location',
	function ($rootScope, $scope, $http, Page, User, $location) {
		
		Page.setTitle("messages");
		User.isLogged();
		
		$http.get('/auctions/rest/Message/all')
		.then(function(response) {
				$scope.messages = response.data.messageRetObject;
				console.log("response: " + response);
				dateCorrection($scope.messages, "message");
			}, function(response){
				console.log("No messages for you.");
			});
		
		$scope.reply = function (message) {
			$rootScope.message = message;
			$location.path('/message');
		}
	}
]);

myApp.controller('messageCtrl', ['$rootScope', '$scope', '$location', 'Page', 'User',
		function($rootScope, $scope, $location, Page, User){
	
	Page.setTitle("Message");
	User.isLogged();
	$scope.User = User;$scope.message
	$scope.message = $rootScope.message;
	$scope.reply = function (message) {
		console.log("reply worx");
		message.messageText = "";
		$rootScope.message = message;
		console.log(message);
		$location.path('/newMessage');
	}
}]);

myApp.controller('newMessageCtrl', ['$rootScope', '$scope', '$http', 'Page', 'NewMessageService', 'User',
	function ($rootScope, $scope, $http, Page, NewMessageService, User) {
		Page.setTitle("New Message");
		User.isLogged();
		$scope.message = $rootScope.message;
		$scope.newMessage = function (message) {
			$http.post('/auctions/rest/Message/new', message)
					.success(function (data) {
						console.log("newMessage called succesfully!");
						$scope.message.messageText = "";
					});
		}
	}
]);

myApp.controller('newAuctionCtrl', ['$scope', '$http', 'Page', 'AuctionService', 'User',
	function ($scope, $http, Page, AuctionService, User) {
		Page.setTitle("newAuction");
		User.isLogged();
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
			AuctionService.newAuction(auction);
			$scope.productName = "";
			$scope.categories = "";
			$scope.buyPrice = "";
			$scope.firstBid = "";
			$scope.startTime = "";$scope.message
			$scope.endTime = "";
			$scope.description = "";
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
			//console.log("Auction ctrl!");
			Page.setTitle("Auction");
			
			$http.post('/auctions/rest/Auction/getAuction', $rootScope.auction).success(function (response){
				if (response.status == "ok") {
					$scope.auction = response;
				}
			});
			
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
			
			$http.post('/auctions/rest/Bid/bids', $scope.auction).success(function (response) {
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
			
			$scope.placeBid = function (auction) {
				console.log(auction);
				if (auction.ammount < auction.currentBid) {
					console.log("NO");
					growl.info("Bid must be higher than current bid!!!.", {title: "new bid check!"});
					auction.ammount = "";
				} else {
					$http.post('/auctions/rest/Bid/new', auction)
					.success(function (data) {
						$location.path('/auctions');
					});
				}
				
			}
			
			$scope.sendMessage = function (auction) {
				$rootScope.message = {};
				$rootScope.message.senderId = auction.ownerId;
				$location.path('/newMessage');
			}
}]);

myApp.controller('manageAuctionCtrl', ['$rootScope', '$scope', '$http', 'Page', 'User', 'AuctionService',
	function ($rootScope, $scope, $http, Page, User, AuctionService) {
		Page.setTitle("manageAuction");
		User.ownsAuction($rootScope.auction);
		$scope.auction = $rootScope.auction;
		
		$scope.update = function() {
			if ($scope.newProductName) {
				$scope.auction.productName = $scope.newProductName;
				$scope.newProductName = null;
			}
			if ($scope.newCategories) {
				$scope.auction.categories = $scope.newCategories;
				$scope.newCategories = null;
			}
			if ($scope.newBuyPrice) {
				$scope.auction.buyPrice = $scope.newBuyPrice;
				$scope.newBuyPrice = null;
			}
			if ($scope.newFirstBid) {
				$scope.auction.firstBid = $scope.newFirstBid;
				$scope.newFirstBid = null;
			}
			if ($scope.newStartTime) {
				$scope.auction.startTime = $scope.newStartTime;
				$scope.newStartTime = null;
			}
			if ($scope.newEndTime) {
				$scope.auction.endTime = $scope.newEndTime;
				$scope.newEndTime = null;
			}
			if ($scope.newDescription) {
				$scope.auction.description = $scope.newDescription;
				$scope.newDescription = null;
			}
			AuctionService.updateAuction($scope.auction);
		}
		
		$scope.delete = function() {
			console.log("deleteAuction");
			AuctionService.deleteAuction($scope.auction);
		}
	}
]);

myApp.controller('notifyCtrl', ['$scope', 'growl',
	function($scope, growl){
		$scope.showInfo = function (){
			growl.info("You 've got a new message.", {title: "Message!"});
		}
}]);

myApp.controller('adminPanelCtrl', ['$rootScope', '$scope', '$location', 'Page', 'User', '$http', '$window',
	function($rootScope, $scope, $location, Page, User, $http, $window){
		
		Page.setTitle("Admin panel");
		User.isAdmin();
		$scope.confirmUser = function (user) {
			$http.post('/auctions/rest/User/confirm', user).success(function (response) {
				console.log(response);
			});
		}
		$http.get('/auctions/rest/User/users').success(function (response) {
			$scope.users = response.userRetObject;
		});
		
		$scope.getXmlAuctions = function () {
			$window.open('/auctions/rest/Admin/auctions');
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