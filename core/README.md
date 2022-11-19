# Dependency: TomlJ

TomlJ be working: 
- Convert anything to CharStream
- CharStream passed to ANTLR Lexer
- Lexer giving out something giving out TomlParseResult
- Parse result can be combined to Toml
- But can a class be serialized?
- YES! Seems to me that MutableTomlTable is there :)
