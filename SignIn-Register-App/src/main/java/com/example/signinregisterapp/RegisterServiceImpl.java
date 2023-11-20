package com.example.signinregisterapp;

public class RegisterServiceImpl implements RegisterService {

    private ValidatorRegister validatorRegister;
    private RegisterRepository registerRepository;

    public RegisterServiceImpl(ValidatorRegister validatorRegister,
                               RegisterRepository registerRepository) {
        this.validatorRegister = validatorRegister;
        this.registerRepository = registerRepository;
    }

    @Override
    public boolean register(Person person) {
        try{
            if(validatorRegister.isCorrectRegistration(person)){
                registerRepository.create(person);
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
