/*
 * Copyright 2014-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.map.repository.config;

import static org.assertj.core.api.Assertions.*;

import lombok.Data;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Integration tests for {@link MapRepositoriesRegistrar} with complete defaulting.
 *
 * @author Christoph Strobl
 * @author Mark Paluch
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class MapRepositoryRegistrarWithFullDefaultingIntegrationTests {

	@Configuration
	@EnableMapRepositories(considerNestedRepositories = true)
	static class Config {

	}

	@Autowired PersonRepository repo;

	@Test // DATAKV-86
	void shouldEnableMapRepositoryCorrectly() {
		assertThat(repo).isNotNull();
	}

	@Data
	static class Person {

		@Id String id;
		String firstname;

	}

	interface PersonRepository extends CrudRepository<Person, String> {

		List<Person> findByFirstname(String firstname);
	}
}
