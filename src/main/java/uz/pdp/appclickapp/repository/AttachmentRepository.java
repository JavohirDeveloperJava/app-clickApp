package uz.pdp.appclickapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appclickapp.entity.Attachment;

import java.util.UUID;
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}

