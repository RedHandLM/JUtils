package design_pattern.factory.abstract_factory.conditioner.impl;

import design_pattern.factory.abstract_factory.conditioner.Air_conditioner;

public class Air_conditioner_Gree implements Air_conditioner {

    @Override
    public void work() {
        System.out.println("格力空调");
    }

}
