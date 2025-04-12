/*
 * Copyright © 2024 <Your Company>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.cdap.wrangler.api.parser;

/**
 * Utility class to convert string values into specific types based on {@link TokenType}.
 * <p>
 * This class is used when interpreting directive arguments that require values like
 * byte sizes ("10KB", "2MB") or time durations ("5s", "100ms").
 */
public class TypeValueConverter {

  /**
   * Converts a string input into the appropriate Java object based on the given {@link TokenType}.
   *
   * @param type  the type of token expected (e.g., BYTE_SIZE, TIME_DURATION)
   * @param value the string value to be converted
   * @return the parsed value as an {@link Object}; typically {@link Long} or {@link String}
   * @throws IllegalArgumentException if the conversion fails or the type is unsupported
   */
  public static Object parse(TokenType type, String value) {
    // Check for null value input and throw an exception if so.
    if (value == null) {
      throw new IllegalArgumentException("Input value cannot be null for TokenType: " + type);
    }

    // Switch statement to handle different TokenType cases.
    switch (type) {
      case BYTE_SIZE:
        // Converts string like "10KB" to bytes (e.g., 10240 bytes)
        return new ByteSize(value).getBytes();

      case TIME_DURATION:
        // Converts string like "5s" to milliseconds (e.g., 5000 ms)
        return new TimeDuration(value).getMilliseconds();

      default:
        // If the TokenType doesn't match known cases, return the original string.
        // This can be expanded for future types if needed.
        return value;
    }
  }
}
