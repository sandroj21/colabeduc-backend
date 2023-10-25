package colabeduc

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/descricao"(resources:"descricaoRest") 
        "/api/projeto"(resources:"projetoRest")
        "/api/tarefa"(resources:"tarefaRest")
        "/api/categoria"(resources:"categoriaRest")
        "/api/grupo"(resources:"grupoRest")
        "/api/metadescricao"(resources:"metadescricaoRest")
        "/api/avaliacao"(resources:"avaliacaoRest")
        
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

    }
}
