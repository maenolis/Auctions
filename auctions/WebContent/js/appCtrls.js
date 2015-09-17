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

myApp.controller('userCtrl', ['$scope', 'User',
	function($scope, User){
		$scope.User = User;
		//console.log("logged : " + User.IsLogged());
}]);

myApp.controller('homeCtrl', ['$scope', 'Page', 'User',
	function($scope, Page, User){
		Page.setTitle("home");
		User.setUser("maenolis");
		User.setIsLogged(true);
		$scope.User = User;
		//console.log("logged : " + User.IsLogged());
}]);

myApp.controller('auctionsCtrl', ['$scope', '$http', 'Page', 'User',
	function ($scope, $http, Page, User) {
		$scope.User = User;
		Page.setTitle("auctions");
		/*$http.get('jsons/auctions.json').success(function(data) {
			console.log(data);
			$scope.auctions = toArray(data);
			console.log($scope.auctions);
			dateCorrection($scope.auctions);
			}*/
			
		$http.get('/auctions/rest/test/auctions')
			.then(function(response) {
				console.log(response);
				console.log(response.data.auctionRetObject[0].description);
				$scope.auctions = response.data.auctionRetObject;
				dateCorrection($scope.auctions);
			}, function(response){
				console.log(response);
			});
		
	}
]);

myApp.controller('messagesCtrl', ['$scope', '$http', 'Page', 'User',
	function ($scope, $http, Page, User) {
		$scope.User = User;
		Page.setTitle("messages");
		$http.get('jsons/messages.json').success(function(data) {
				$scope.messages = toArray(data);
				//dateCorrection($scope.messages);
			}
		);
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

myApp.controller('auctionCtrl', ['$scope', '$location', 'Page', 'User', function($scope, $location, Page, User){
	console.log("Auction ctrl!");
	Page.setTitle("Auction");
	$scope.User = User;
}]);

myApp.controller('messageCtrl', ['$scope', '$location', 'Page', 'User', function($scope, $location, Page, User){
	console.log("Message ctrl!");
	Page.setTitle("Message");
	$scope.User = User;
	$scope.User.setUser("Manolis message")
}]);

myApp.controller('userPageCtrl', ['$scope', '$location', 'Page', 'User', function($scope, $location, Page, User){
	console.log("UserPage ctrl!");
	Page.setTitle("User");home
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

function dateCorrection(array) {
	
	var myDateArray = "2008-03-02 33:33:33".split(/[-: ]/);
	
	var myDateArray;
	for (var i in array) {
		myDateArray = array[i].startTime.split(/[-: ]/);
		array[i].startTime = new Date(myDateArray[2], myDateArray[1] - 1, myDateArray[0], myDateArray[3], myDateArray[4], myDateArray[5], 0);
		myDateArray = array[i].endTime.split(/[-: ]/);
		array[i].endTime = new Date(myDateArray[2], myDateArray[1] - 1, myDateArray[0], myDateArray[3], myDateArray[4], myDateArray[5], 0);
	}
}