package hr.ferit.imeprezime.naziv_projekta

class Robot(
    var x: Int,
    var y: Int
) {
    fun goRight(steps: Int) {
        this.x += steps
    }
    fun goLeft(steps: Int){
        this.x -= steps
    }
    fun goDown(steps: Int){
        this.y -= steps
    }
    fun goUp(steps: Int){
        this.y += steps
    }
    fun getLocation() : String {
        return "Location: (${this.x}, ${this.y})"
    }
}