export const transaction = async (client, callback) => {
  try {
      await client.query('BEGIN');
      try {
          await callback(client);
          await client.query('COMMIT');
      } catch (error) {
          await client.query('ROLLBACK');
          console.error(error.stack)        
      }
  } finally {
      client.release();
  }
};