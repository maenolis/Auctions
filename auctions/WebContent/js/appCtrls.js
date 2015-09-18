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
		
			
		$http.get('/auctions/rest/test/auctions')
			.then(function(response) {
				console.log(response);
				console.log(response.data.auctionRetObject[0].description);
				$scope.auctions = response.data.auctionRetObject;
				dateCorrection($scope.auctions, "auction");
			}, function(response){
				console.log(response);
			});
		
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

myApp.controller('auctionCtrl', ['$rootScope', '$scope', '$location', 'Page', 'User',
		function($rootScope, $scope, $location, Page, User){
			console.log("Auction ctrl!");
			Page.setTitle("Auction");
			$scope.User = User;
			$scope.auction = $rootScope.auction;
			$scope.bid = function (auction) {
				console.log("bid worx.");
				console.log(auction);
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
	} else if (type == "message") {
		for (var i in array) {
			myDateArray = array[i].time.split(/[-: ]/);
			array[i].time = new Date(myDateArray[2], myDateArray[1] - 1, myDateArray[0], myDateArray[3], myDateArray[4], myDateArray[5], 0);
		}
	}
	
}