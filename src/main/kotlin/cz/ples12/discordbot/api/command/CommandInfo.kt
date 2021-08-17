package cz.ples12.discordbot.api.command

@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
annotation class CommandInfo(

    val names: Array<String>,

    val description: String = "No description provided."
)
