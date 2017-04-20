package design_pattern.factory.abstract_factory.conditioner.impl;

import design_pattern.factory.abstract_factory.conditioner.Air_conditioner;

public class Air_conditioner_Haier implements Air_conditioner {

    @Override
    public void work() {
        System.out.println("海尔空调");
    }

}
