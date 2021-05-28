/**
 * 
 */
package main.remote.shared.common;

/**
 * @author thaodd
 *
 * Khai báo một số hàm dùng chung
 */
public interface ICommon {
	public String getLastActionInfo();

        /**
         * Tìm đối tượng entity theo khóa
         * @param id
         * @param entityClass
         * @return
         */
        public Object findById(Object id,Class<?> entityClass);

        /**
         * Thêm mới đối tượng entity
         * @param object
         * @return
         */
        public boolean insert(Object object);

        /**
         * Cập nhật đối tượng entity
         * @param object
         * @return
         */
        public boolean update(Object object);

        /**
         * Xóa đối tượng entity
         * @param id
         * @param entityClass
         */
        public boolean delete(Object id,Class<?> entityClass);

	//public void initWorkingInfo(WorkingInfo oWI);

        //public WorkingInfo getWorkingInfo();
}
