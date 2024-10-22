//package com.blogsite.service;
//import com.akeyless.AkeylessClient;
//import com.akeyless.AkeylessClientImpl;
//import com.akeyless.api.exceptions.ApiCommunicationException;
//import com.akeyless.api.exceptions.CredentialsNotFoundException;
//import com.akeyless.api.exceptions.InvalidCredentialsException;
//import com.akeyless.api.exceptions.InvalidParamException;
//import com.akeyless.api.exceptions.MissingRequiredParamException;
//import com.akeyless.api.exceptions.NotFoundException;
//import com.akeyless.api.exceptions.UnauthorizedUserException;
//import com.akeyless.crypto.exceptions.BadCiphertextException;
//import com.akeyless.customerfragment.exceptions.CustomerFragmentNotFoundException;
//import com.akeyless.exceptions.AkeylessCryptoException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.security.InvalidKeyException;
//
//@Service
//public class SecretService {
//
//    @Autowired
//    private AkeylessClient akeylessClient;
//
//    public String getSecret(String secretPath) {
//        try {
//            return   akeylessClient.getSecretValue(secretPath);
//        }
//         catch (InvalidParamException e) {
//            throw new RuntimeException(e);
//        } catch (MissingRequiredParamException e) {
//            throw new RuntimeException(e);
//        } catch (ApiCommunicationException e) {
//            throw new RuntimeException(e);
//        } catch (InvalidCredentialsException e) {
//            throw new RuntimeException(e);
//        } catch (CredentialsNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (UnauthorizedUserException e) {
//            throw new RuntimeException(e);
//        } catch (NotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (InvalidKeyException e) {
//            throw new RuntimeException(e);
//        } catch (BadCiphertextException e) {
//            throw new RuntimeException(e);
//        } catch (AkeylessCryptoException e) {
//            throw new RuntimeException(e);
//        } catch (CustomerFragmentNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
