/*
 * Copyright 2022 YDWK inc.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.ydwk.bot.handler.slash

import io.github.classgraph.ClassGraph
import io.github.ydwk.bot.logger
import io.github.ydwk.ydwk.YDWK

class AutoSlashAdder(ydwk: YDWK) : SlashHandler(ydwk) {
    private fun loadCommands(): List<Class<out SlashCommandExtender>> {
        ClassGraph().acceptPackages(acceptedPackages()).enableClassInfo().scan().use { result ->
            return result.allClasses
                .filter { it.implementsInterface(SlashCommandExtender::class.java) }
                .mapNotNull { classInfo ->
                    runCatching { classInfo.loadClass() as? Class<out SlashCommandExtender> }
                        .onFailure { logger.error("Failed to load class ${classInfo.name}", it) }
                        .getOrNull()
                }
        }
    }

    init {
        registerSlashCommands(loadCommands().map { it.getConstructor().newInstance() })
    }
}

private fun ClassGraph.acceptPackages(acceptedPackages: List<String>): ClassGraph {
    return this.acceptPackages(*acceptedPackages.toTypedArray())
}

fun acceptedPackages(): List<String> {
    val mainPackageName = "io.github.ydwk.bot.commands"
    return listOf("$mainPackageName.simple, $mainPackageName.moderation")
}
