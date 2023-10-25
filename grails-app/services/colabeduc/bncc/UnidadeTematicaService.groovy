package colabeduc.bncc

import grails.gorm.services.Service

@Service(UnidadeTematica)
interface UnidadeTematicaService {

    UnidadeTematica get(Serializable id)

    List<UnidadeTematica> list(Map args)

    Long count()

    void delete(Serializable id)

    UnidadeTematica save(UnidadeTematica unidadeTematica)

}