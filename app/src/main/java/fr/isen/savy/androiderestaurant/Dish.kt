package fr.isen.savy.androiderestaurant

class Dish(val name: String) {

    companion object {

        fun addDish(nom: Array<String>): ArrayList<Dish> {
            val dish = ArrayList<Dish>()
            for(value in nom){
                dish.add(Dish(value))
            }

            return dish
        }
    }
}