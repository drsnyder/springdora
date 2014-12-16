# Spring Pandora

This is a reference implementation of the basic functional required for a new
project at Wikia.

## Usage

To run the tests, run `gradle test`. To run the server, run `gradle run`. The
latter should start an HTTP server on port 8080.

## Routes

The application uses http://muppet.wikia.com as the source for Mercury
requests. To get an article from the Muppet Wikia:

   curl http://localhost:8080/articles/Kermit%20the%20Frog

This will return a representation of the Kermit the Frog article in
`application/hal+json` that contains the `content`, `id`, `title`, and
`thumbnail`.
