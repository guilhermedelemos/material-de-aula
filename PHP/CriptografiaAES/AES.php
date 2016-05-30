<?php
class AES {

    const MODE_CBC = 'cbc';
    const MODE_CFB = 'cfb';
    const MODE_ECB = 'ecb';
    const MODE_NOFB = 'nofb';
    const MODE_OFB = 'ofb';
    const MODE_STREAM = 'stream';

    protected $key;
    protected $cipher;
    protected $mode;
    protected $IV;

    /**
     * 
     * @param string $key
     * @param string $mode
     * @param integer $blockSize
     */
    function __construct($key = null, $mode = null, $blockSize = null) {
        $this->setKey($key);
        $this->setMode($mode);
        $this->setBlockSize($blockSize);
        $this->setIV("");
    }

    /**
     * 
     * @param string $key
     */
    public function setKey($key) {
        $this->key = $key;
        switch (strlen($key)) {
            case 16:
                $this->setBlockSize(128); // 16bytes*8bit=128bit
                break;
            case 24:
                $this->setBlockSize(192); // 24bytes*8bit=192bit
                break;
            case 32:
                $this->setBlockSize(256); // 32bytes*8bit=256bit
                break;
        }
    }

    /**
     * 
     * @param integer $blockSize
     */
    public function setBlockSize($blockSize) {
        switch ($blockSize) {
            case 128:
                $this->cipher = MCRYPT_RIJNDAEL_128;
                break;

            case 192:
                $this->cipher = MCRYPT_RIJNDAEL_192;
                break;

            case 256:
                $this->cipher = MCRYPT_RIJNDAEL_256;
                break;
        }
    }

    /**
     * 
     * @param string $mode
     */
    public function setMode($mode) {
        switch ($mode) {
            case AES::MODE_CBC:
                $this->mode = MCRYPT_MODE_CBC;
                break;
            case AES::MODE_CFB:
                $this->mode = MCRYPT_MODE_CFB;
                break;
            case AES::MODE_ECB:
                $this->mode = MCRYPT_MODE_ECB;
                break;
            case AES::MODE_NOFB:
                $this->mode = MCRYPT_MODE_NOFB;
                break;
            case AES::MODE_OFB:
                $this->mode = MCRYPT_MODE_OFB;
                break;
            case AES::MODE_STREAM:
                $this->mode = MCRYPT_MODE_STREAM;
                break;
            default:
                $this->mode = MCRYPT_MODE_ECB;
                break;
        }
    }

    /**
     * 
     * @return boolean
     */
    public function validateParams() {
        if ($this->key != null &&
                $this->cipher != null) {
            return true;
        } else {
            return FALSE;
        }
    }

    /**
     * 
     * @param string $IV
     */
    public function setIV($IV) {
        $this->IV = $IV;
    }

    /**
     * 
     * @return string
     */
    protected function getIV() {
        if ($this->IV == "") {
            $this->IV = mcrypt_create_iv(mcrypt_get_iv_size($this->cipher, $this->mode), MCRYPT_RAND);
        }
        return $this->IV;
    }

    /**
     * 
     * @param string $data
     * @return string
     * @throws Exception
     */
    public function encrypt($data) {
        if ($this->validateParams() && !empty($data)) {
            return trim(
                        base64_encode(
                            mcrypt_encrypt(
                                $this->cipher, 
                                $this->key, 
                                $data, 
                                $this->mode, 
                                $this->getIV()
                            )
                        )
                   );
        } else {
            throw new Exception('Invlid params!');
        }
    }

    /**
     * 
     * @param string $data
     * @return string
     * @throws Exception
     */
    public function decrypt($data) {
        if ($this->validateParams() && !empty($data)) {
            return trim(
                        mcrypt_decrypt(
                            $this->cipher, 
                            $this->key, 
                            base64_decode($data), 
                            $this->mode, 
                            $this->getIV()
                        )
                   );
        } else {
            throw new Exception('Invlid params!');
        }
    }

}

