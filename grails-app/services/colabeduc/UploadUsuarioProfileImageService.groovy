package colabeduc

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileStatic

@SuppressWarnings('GrailsStatelessService')
@CompileStatic
class UploadUsuarioProfileImageService implements GrailsConfigurationAware {

    UsuarioDataService usuarioDataService

    String cdnFolder
    String cdnRootUrl

    @Override
    void setConfiguration(Config co) {
        cdnFolder = co.getRequiredProperty('grails.guides.cdnFolder')
        cdnRootUrl = co.getRequiredProperty('grails.guides.cdnRootUrl')
    }

    @SuppressWarnings('JavaIoPackageAccess')
    Usuario uploadProfileImage(ProfileImageCommand cmd) {

        String filename = cmd.profileImageFile.originalFilename
        String folderPath = "${cdnFolder}/usuario/${cmd.id}"
        File folder = new File(folderPath)
        if ( !folder.exists() ) {
            folder.mkdirs()
        }
        String path = "${folderPath}/${filename}"
        cmd.profileImageFile.transferTo(new File(path))

        String profileImageUrl = "${cdnRootUrl}//usuario/${cmd.id}/${filename}"
        Usuario poi = usuarioDataService.updateProfileImageUrl(cmd.id, cmd.version, profileImageUrl)

        if ( !poi || poi.hasErrors() ) {
            File f = new File(path)
            f.delete()
        }
        poi
    }

    
}