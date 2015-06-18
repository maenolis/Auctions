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
		//console.log("logged : " + User.IsLogged());
}]);

myApp.controller('auctionsCtrl', ['$scope', '$http', 'Page',
	function ($scope, $http, Page) {
		Page.setTitle("auctions");
		$http.get('jsons/auctions.json').success(function(data) {
				$scope.auctions = toArray(data);
				dateCorrection($scope.auctions);
			}
		);
	}
]);

myApp.controller('messagesCtrl', ['$scope', '$http', 'Page',
	function ($scope, $http, Page) {
		Page.setTitle("messages");
		$http.get('jsons/messages.json').success(function(data) {
				$scope.messages = toArray(data);
				dateCorrection($scope.messages);
			}
		);
	}
]);

myApp.controller('newMessageCtrl', ['$scope', '$http', 'Page',
	function ($scope, $http, Page) {
		Page.setTitle("New Message");
		/*$http.get().success(function(data) {
				
			}
		);*/
	}
]);

myApp.controller('newAuctionCtrl', ['$scope', '$http', 'Page',
	function ($scope, $http, Page) {
		Page.setTitle("newAuction");
	}
]);

myApp.controller('getClassCtrl', ['$scope', '$location', function($scope, $location){
	$scope.isActive = function (viewLocation) {
		return viewLocation == $location.path();
	}
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
	
	for (var i in array) {
		array[i].startDate = new Date(array[i].startDate);
		array[i].endDate = new Date(array[i].endDate);
	}
}