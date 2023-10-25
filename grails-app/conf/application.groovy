

// Added by the Spring Security Core plugin:
//grails.plugin.springsecurity.logout.postOnly = false
//grails.plugin.springsecurity.auth.loginFormUrl = '/login/auth'
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/projeto/index' 
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.userLookup.userDomainClassName = 'colabeduc.Usuario'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'colabeduc.UsuarioPapel'
grails.plugin.springsecurity.authority.className = 'colabeduc.Papel'
grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugin.springsecurity.interceptUrlMap = [

	[pattern: '/',               		access: ['permitAll']],
	[pattern: '/error',          		access: ['permitAll']],
	[pattern: '/index',          		access: ['permitAll']],
	[pattern: '/index.gsp',      		access: ['permitAll']],
	[pattern: '/shutdown',       		access: ['permitAll']],
	[pattern: '/assets/**',      		access: ['permitAll']],
	[pattern: '/src/**',      			access: ['permitAll']],
	[pattern: '/**/js/**',       		access: ['permitAll']],
	[pattern: '/**/css/**',      		access: ['permitAll']],
	[pattern: '/**/images/**',   		access: ['permitAll']],
	[pattern: '/**/fonts/**',           access: ['permitAll']],

	[pattern: '/login/**',       		access: ['permitAll']],
	[pattern: '/logout/**',      		access: ['permitAll']],
	[pattern: '/emailSender/**',        access: ['permitAll']],

	[pattern: '/papel/**', access: ['ROLE_ADMIN']],
	[pattern: '/usuariopapel/**', access: ['ROLE_ADMIN']],
	[pattern: '/descricao/**', access: ['isAuthenticated()']],
    [pattern: '/metadescricao/**', access: ['ROLE_ADMIN']],
    [pattern: '/projeto/**', access: ['isAuthenticated()']],
    [pattern: '/tarefa/**', access: ['isAuthenticated()']],
    [pattern: '/categoria/**', access: ['isAuthenticated()']],
	[pattern: '/usuario/', access: ['ROLE_ADMIN']],
	[pattern: '/cadastro/usuario', access: ['permitAll'], HttpMethod:'POST'],
	[pattern: '/usuario/delete/**', access: ['ROLE_ADMIN']],
    [pattern: '/usuario/index/**', access: ['ROLE_ADMIN']],
	[pattern: '/usuario/show/**', access: ['ROLE_ADMIN']],
	[pattern: '/usuario/edit/**', access: ['ROLE_ADMIN']],
	[pattern: '/usuario/recoveryPassword', access: ['permitAll']],
	[pattern: '/usuario/recoveryUsername', access: ['permitAll']],
    [pattern: '/**', access: ['permitAll']],
	[pattern: '/estatisticas/**', access: ['permitAll']]
]


grails.plugin.springsecurity.filterChain.chainMap = [
   	//Stateless chain
    [
	    pattern: '/api/**',
	    filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
    ],
    //Traditional, stateful chain
    [
        pattern: '/**',
        filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'
    ]

]

//grails.plugin.springsecurity.rest.token.storage.useJwt= false
/*grails.plugin.springsecurity.rest.token.storage.jwt.useSignedJwt=true
grails.plugin.springsecurity.rest.token.storage.jwt.expiration=3600*/
grails.plugin.springsecurity.rest.token.storage.jwt.secret='qrD6h8K6S9503Q06Y6Rfk21TErImPYqa'

grails.plugin.springsecurity.rest.logout.endpointUrl = '/api/logout'
grails.plugin.springsecurity.rest.token.validation.useBearerToken = false
grails.plugin.springsecurity.rest.token.validation.headerName = 'X-Auth-Token'



