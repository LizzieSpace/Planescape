# Contributing to Planescape

## Development Guidelines

### Mixin Development

- Always document Mixins thoroughly with clear explanations of injection points
- Use appropriate injection points (@At annotations) based on the target lifecycle event
- Follow the existing pattern of separating concerns into distinct Mixin classes
- Include detailed JavaDoc comments explaining the purpose and functionality

### API Integration

- When integrating with PlanescapeAPI, ensure proper initialization and cleanup
- Use the established accessor patterns for API method calls
- Handle server lifecycle events appropriately (initialization, shutdown)
- Avoid direct API manipulation outside designated Mixin points

### Testing Requirements

- Test all Mixin integrations thoroughly on both dedicated and integrated servers
- Verify proper API initialization and shutdown sequences
- Ensure a clean shutdown without resource leaks
- Document any specific test scenarios in the pull request

### Code Style

- Follow existing code formatting and organization patterns
- Maintain comprehensive documentation for all public APIs
- Use meaningful names for Mixin methods that reflect their purpose
- Include appropriate null checks and error handling

### Pull Request Process

1. Create a feature branch from 'main'
2. Follow the established code style and documentation standards
3. Test thoroughly on multiple server configurations
4. Update relevant documentation
5. Submit PR with a detailed description of changes

### API Versioning

- Maintain backward compatibility when modifying API integration points
- Document any breaking changes clearly
- Follow semantic versioning for API changes

## Need Help?

Feel free to open an issue for:

- Questions about Mixin implementation
- API integration challenges
- Clarification on contribution requirements
