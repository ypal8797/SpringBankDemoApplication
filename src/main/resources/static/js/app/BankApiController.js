'use strict';

angular.module('bankApp').controller('BankApiController',
    ['BankService', '$scope',  function( BankService, $scope) {

        var self = this;
        self.account = {};
        self.accounts=[];

        self.submit = submit;
        self.getAllAccounts = getAllUsers;
        self.createAccount = createUser;
        self.updateAccount = updateUser;
        self.removeAccount = removeUser;
        self.editAccount = editAccount;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.account.id === undefined || self.account.id === null) {
                console.log('Saving New Account', self.account);
                createAccount(self.account);
            } else {
                updateAccount(self.account, self.account.id);
                console.log('Account updated with id ', self.account.id);
            }
        }

        function createAccount(account) {
            console.log('About to create account');
            UserService.createAccount(account)
                .then(
                    function (response) {
                        console.log('Account created successfully');
                        self.successMessage = 'Account created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.user={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating User');
                        self.errorMessage = 'Error while creating Account: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateAccount(account, id){
            console.log('About to update Account');
            UserService.updateAccount(account, id)
                .then(
                    function (response){
                        console.log('Account updated successfully');
                        self.successMessage='Account updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Account');
                        self.errorMessage='Error while updating Account '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeAccount(id){
            console.log('About to remove Account with id '+id);
            UserService.removeAccount(id)
                .then(
                    function(){
                        console.log('Account '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing account '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllAccounts(){
            return UserService.getAllAccounts();
        }

        function editAccount(id) {
            self.successMessage='';
            self.errorMessage='';
            UserService.getUser(id).then(
                function (account) {
                    self.account = account;
                },
                function (errResponse) {
                    console.error('Error while removing user ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.user={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);