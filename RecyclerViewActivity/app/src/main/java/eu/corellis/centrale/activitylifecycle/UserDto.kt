package eu.corellis.centrale.activitylifecycle

class UserDto {
    var name: String = ""
    var comment: String = ""

    constructor() {}

    constructor(name: String, comment: String) {
        this.name = name
        this.comment = comment
    }
}