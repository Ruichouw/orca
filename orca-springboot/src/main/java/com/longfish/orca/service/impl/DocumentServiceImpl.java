package com.longfish.orca.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfish.orca.constant.DatabaseConstant;
import com.longfish.orca.context.BaseContext;
import com.longfish.orca.exception.BizException;
import com.longfish.orca.mapper.DocumentMapper;
import com.longfish.orca.pojo.dto.DocumentDTO;
import com.longfish.orca.pojo.dto.PageDTO;
import com.longfish.orca.pojo.entity.Document;
import com.longfish.orca.pojo.entity.Folder;
import com.longfish.orca.pojo.vo.DocumentAbstractVO;
import com.longfish.orca.pojo.vo.PageVO;
import com.longfish.orca.service.IDocumentService;
import com.longfish.orca.service.IFolderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author longfish
 * @since 2024-05-16
 */
@Service
@Slf4j
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document> implements IDocumentService {

    @Autowired
    private IFolderService folderService;

    @Transactional
    @Override
    public void create(DocumentDTO documentDTO) {
        Long currentId = BaseContext.getCurrentId();
        if (documentDTO.getPath() == null || documentDTO.getPath().equals("")) documentDTO.setPath("/");
        Document save = BeanUtil.copyProperties(documentDTO, Document.class);
        save.setUserId(currentId);
        save.setCreateTime(LocalDateTime.now());
        save.setUpdateTime(LocalDateTime.now());
        save(save);

        if (!folderService.lambdaQuery().eq(Folder::getUserId, currentId).exists()) {
            folderService.save(Folder.builder()
                    .userId(currentId)
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build());
        }
    }

    @Override
    public PageVO<DocumentAbstractVO> listAll(PageDTO pageDTO) {
        Page<Document> page = Page.of(pageDTO.getPageNo(), pageDTO.getPageSize());
        String sortBy = pageDTO.getSortBy();
        try {
            query().eq(DatabaseConstant.USER_ID, BaseContext.getCurrentId())
                    .eq(DatabaseConstant.IS_DELETED, 0)
                    .eq(DatabaseConstant.IS_TOP, 0)
                    .eq(DatabaseConstant.IS_LOCKED, 0)
                    .orderBy(sortBy != null && !sortBy.equals(""), pageDTO.getIsAsc(), sortBy)
                    .page(page);
        } catch (Exception e) {
            throw new BizException("未知排序字段");
        }
        List<DocumentAbstractVO> returnList = new ArrayList<>();
        page.getRecords().forEach(document -> {
            DocumentAbstractVO vo = BeanUtil.copyProperties(document, DocumentAbstractVO.class);
            returnList.add(vo);
        });
        return new PageVO<>(returnList);
    }

    @Override
    public List<DocumentAbstractVO> top() {
        List<Document> result = lambdaQuery().eq(Document::getUserId, BaseContext.getCurrentId())
                .eq(Document::getIsDeleted, 0)
                .eq(Document::getIsLocked, 0)
                .eq(Document::getIsTop, 1)
                .orderBy(true, false, Document::getUpdateTime)
                .list();
        List<DocumentAbstractVO> returnList = new ArrayList<>();
        result.forEach(document -> {
            DocumentAbstractVO vo = BeanUtil.copyProperties(document, DocumentAbstractVO.class);
            returnList.add(vo);
        });
        return returnList;
    }

    @Override
    public List<DocumentAbstractVO> trash() {
        List<Document> result = lambdaQuery().eq(Document::getUserId, BaseContext.getCurrentId())
                .eq(Document::getIsDeleted, 1)
                .eq(Document::getIsLocked, 0)
                .eq(Document::getIsTop, 0)
                .orderBy(true, false, Document::getUpdateTime)
                .list();
        List<DocumentAbstractVO> returnList = new ArrayList<>();
        result.forEach(document -> {
            DocumentAbstractVO vo = BeanUtil.copyProperties(document, DocumentAbstractVO.class);
            returnList.add(vo);
        });
        return returnList;
    }

    @Override
    public List<DocumentAbstractVO> path(String path) {
        if (path == null || path.equals("")) path = "/";
        List<Document> result = lambdaQuery().eq(Document::getUserId, BaseContext.getCurrentId())
                .eq(Document::getIsDeleted, 1)
                .eq(Document::getIsLocked, 0)
                .eq(Document::getIsTop, 0)
                .eq(Document::getPath, path)
                .orderBy(true, false, Document::getUpdateTime)
                .list();
        List<DocumentAbstractVO> returnList = new ArrayList<>();
        result.forEach(document -> {
            DocumentAbstractVO vo = BeanUtil.copyProperties(document, DocumentAbstractVO.class);
            returnList.add(vo);
        });
        return returnList;
    }

    @Override
    public Document id(Long id) {
        return lambdaQuery().eq(Document::getUserId, BaseContext.getCurrentId())
                .eq(Document::getId, id)
                .eq(Document::getIsLocked, 0)
                .one();
    }

    @Override
    public void deleteById(Long id) {
        lambdaUpdate().set(Document::getIsDeleted, 1)
                .eq(Document::getUserId, BaseContext.getCurrentId())
                .eq(Document::getId, id)
                .eq(Document::getIsDeleted, 0)
                .eq(Document::getIsLocked, 0)
                .update();
    }

    @Override
    public void deleteByIdBatch(List<Long> ids) {
        Long currentId = BaseContext.getCurrentId();
        List<Document> result = lambdaQuery()
                .eq(Document::getUserId, currentId)
                .eq(Document::getIsDeleted, 0)
                .eq(Document::getIsLocked, 0)
                .in(Document::getId, ids)
                .list();
        result.forEach(doc -> doc.setIsDeleted(true));
        updateBatchById(result);
    }

    @Override
    public void deleteByIdTruly(Long id) {
        Document remove = lambdaQuery()
                .eq(Document::getUserId, BaseContext.getCurrentId())
                .eq(Document::getId, id)
                .eq(Document::getIsDeleted, 1)
                .eq(Document::getIsLocked, 0)
                .one();
        removeById(remove);
    }

    @Override
    public void deleteByIdBatchTruly(List<Long> ids) {
        List<Document> result = lambdaQuery()
                .eq(Document::getUserId, BaseContext.getCurrentId())
                .eq(Document::getIsDeleted, 1)
                .eq(Document::getIsLocked, 0)
                .in(Document::getId, ids)
                .list();
        List<Long> remove = new ArrayList<>();
        result.forEach(r -> remove.add(r.getId()));
        removeByIds(remove);
    }

    @Override
    public void restore(Long id) {
        lambdaUpdate().set(Document::getIsDeleted, 0)
                .eq(Document::getUserId, BaseContext.getCurrentId())
                .eq(Document::getId, id)
                .eq(Document::getIsDeleted, 1)
                .eq(Document::getIsLocked, 0)
                .update();
    }

    @Override
    public void restoreBatch(List<Long> ids) {
        Long currentId = BaseContext.getCurrentId();
        List<Document> result = lambdaQuery()
                .eq(Document::getUserId, currentId)
                .eq(Document::getIsDeleted, 1)
                .eq(Document::getIsLocked, 0)
                .in(Document::getId, ids)
                .list();
        result.forEach(doc -> doc.setIsDeleted(false));
        updateBatchById(result);
    }
}
